package org.mybyteman;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;

public class MyTransformer implements ClassFileTransformer {

    @Override
	public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		byte[] byteCode = classfileBuffer;
		byte[] toAdd = new byte[] {(byte) 0xb2, 0x00, 0x05, 0x04, (byte) 0x60, (byte) 0xb3, 0x00,0x05};

		//Add instrumentation to Sample class alone
		if (className.equals("org/my/SimpleProgram2")) {
			try {
				ClassPool classPool = ClassPool.getDefault();
				System.out.println(classPool);
				CtClass bufferWriter = classPool.get​("org.mybyteman.BufferWriter");
				System.out.println(bufferWriter);
				CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
				
				CtMethod[] methods = ctClass.getDeclaredMethods();
				// for (CtMethod method : methods) {
				//     System.out.println(method.getName());
				//     System.out.println(method.getLongName());
				// }

                CtMethod method = ctClass.getDeclaredMethod("benchmark");
				// method.insertAt(19, "org.mybyteman.MoodyCamelBuffer.put(counter);");
				method.insertAt(31, "org.mybyteman.MoodyCamelBuffer.put(counter);");
                CtMethod method2 = ctClass.getDeclaredMethod("main");
				method2.insertAfter("System.out.println(\"total\");");
				method2.insertAfter("org.mybyteman.MoodyCamelBuffer.printAll();");
				// method2.insertAfter("System.out.println(org.mybyteman.MoodyCamelBuffer.ringBuffer.getCursor());");
				// method2.insertAfter("System.out.println(org.mybyteman.MoodyCamelBuffer.ringBuffer.next());");
				// CodeAttribute ca = method.getMethodInfo().getCodeAttribute();
				// CodeIterator ci = ca.iterator();
				// ci.insertAt(18,toAdd);

                // int result = method.insertAt(19, "counter++;");
				// System.out.println(result);
				

				byteCode = ctClass.toBytecode();

                FileOutputStream out = new FileOutputStream("newTransformedClass");
                out.write(byteCode);
                out.close();
                

				ctClass.detach();
			} catch (Throwable ex) {
				System.out.println("Exception: " + ex);
				ex.printStackTrace();
			}
		}
		return byteCode;
	}
}