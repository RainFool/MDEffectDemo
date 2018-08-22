package push.rainfool.com.processor;

import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import push.rainfool.com.anotation.BindAnnotation;

/**
 * @author rainfool
 */
@AutoService(Processor.class)
public class BindProcessor extends AbstractProcessor {

    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        System.out.println("rainfool,init");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {

        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(BindAnnotation.class.getCanonicalName());
        return supportTypes;
    }
}
