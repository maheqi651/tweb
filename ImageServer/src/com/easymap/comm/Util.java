package com.easymap.comm;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import sun.misc.BASE64Decoder;

public class Util {
	
	/*
	    * 将加密的人员头像解析
	    */
	   public byte[] generateImage(String imgStr) {
			if (imgStr == null) {
				return null;
			}
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				byte[] bytes = decoder.decodeBuffer(imgStr);
				for (int i = 0; i < bytes.length; ++i) {
					if (bytes[i] < 0) {
						bytes[i] += 256;
					}
				}
				return bytes;
			} catch (Exception e) {
				return null;
			}
		}
}
