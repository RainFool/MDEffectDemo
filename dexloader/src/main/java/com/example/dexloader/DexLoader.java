package com.example.dexloader;

import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class DexLoader {

    private static final String DEX_SUFFIX = ".dex";
    private static final String APK_SUFFIX = ".apk";
    private static final String JAR_SUFFIX = ".jar";
    private static final String ZIP_SUFFIX = ".zip";
    public static final String DEX_DIR = "odex";
    private static final String OPTIMIZE_DEX_DIR = "optimize_dex";
    private static HashSet<File> loadedDex = new HashSet<>();

    static {
        loadedDex.clear();
    }

    /**
     * 加载补丁，使用默认目录：data/data/包名/files/odex
     *
     * @param context
     */
    public static void loadFixedDex(Context context) {
        loadFixedDex(context, null);
    }

    public static void loadFixedDex(Context context, File patchFilesDir) {
        if (context == null) return;
        File fileDir;
        if (patchFilesDir != null) {
            fileDir = patchFilesDir;
        } else {
            // data/data/包名/files/odex（这个可以任意位置）
            fileDir = new File(context.getFilesDir(), DEX_DIR);
        }

        File[] listFiles = fileDir.listFiles();
        for (File file : listFiles) {
            if (file.getName().startsWith("classes") && isMightContainsDex(file)) {
                loadedDex.add(file);
            }
        }

        doDexInject(context, loadedDex);
    }

    private static void doDexInject(Context context, HashSet<File> loadedDex) {
        String optimizeDir = context.getFilesDir().getAbsolutePath() + File.separator + OPTIMIZE_DEX_DIR;// data/data/包名/files/optimize_dex（这个必须是自己程序下的目录）
        File fopt = new File(optimizeDir);
        if (!fopt.exists()) {
            fopt.mkdirs();
        }
        try {
            PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
            for (File dex : loadedDex) {
                DexClassLoader dexClassLoader = new DexClassLoader(dex.getAbsolutePath(), fopt.getAbsolutePath(), null, pathClassLoader);
                Object dexPathList = getPathList(dexClassLoader);
                Object pathPathList = getPathList(pathClassLoader);
                Object leftDexElements = getDexElements(dexPathList);
                Object rightDexElements = getDexElements(pathPathList);

                Object dexElements = combineArray(leftDexElements, rightDexElements);
                Object pathList = getPathList(pathClassLoader);
                setField(pathList, pathList.getClass(), "dexElements", dexElements);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isMightContainsDex(File file) {
        return file.getName().endsWith(DEX_SUFFIX)
                || file.getName().endsWith(APK_SUFFIX)
                || file.getName().endsWith(JAR_SUFFIX)
                || file.getName().endsWith(ZIP_SUFFIX);
    }

    /**
     * 反射给对象中的属性重新赋值
     */
    private static void setField(Object obj, Class<?> cl, String field, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = cl.getDeclaredField(field);
        declaredField.setAccessible(true);
        declaredField.set(obj, value);
    }

    /**
     * 反射得到类加载器中的pathList对象
     */
    private static Object getPathList(Object baseDexClassLoader) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return getField(baseDexClassLoader, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    /**
     * 反射得到对象中的属性值
     */
    private static Object getField(Object obj, Class<?> cl, String field) throws NoSuchFieldException, IllegalAccessException {
        Field localField = cl.getDeclaredField(field);
        localField.setAccessible(true);
        return localField.get(obj);
    }

    /**
     * 反射得到pathList中的dexElements
     */
    private static Object getDexElements(Object pathList) throws NoSuchFieldException, IllegalAccessException {
        return getField(pathList, pathList.getClass(), "dexElements");
    }

    /**
     * 数组合并
     */
    private static Object combineArray(Object arrayLhs, Object arrayRhs) {
        Class<?> componentType = arrayLhs.getClass().getComponentType();
        int i = Array.getLength(arrayLhs);// 得到左数组长度（补丁数组）
        int j = Array.getLength(arrayRhs);// 得到原dex数组长度
        int k = i + j;// 得到总数组长度（补丁数组+原dex数组）
        Object result = Array.newInstance(componentType, k);// 创建一个类型为componentType，长度为k的新数组
        System.arraycopy(arrayLhs, 0, result, 0, i);
        System.arraycopy(arrayRhs, 0, result, i, j);
        return result;
    }


}
