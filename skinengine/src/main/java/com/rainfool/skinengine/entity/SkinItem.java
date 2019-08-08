package com.rainfool.skinengine.entity;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SkinItem {
	
	public View view;
	
	public List<SkinAttr> attrs;
	
	public SkinItem(){
		attrs = new ArrayList<SkinAttr>();
	}

	@Override
	public String toString() {
		return "SkinItem [view=" + view.getClass().getSimpleName() + ", attrs=" + attrs + "]";
	}
}
