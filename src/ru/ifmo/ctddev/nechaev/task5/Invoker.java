package ru.ifmo.ctddev.nechaev.task5;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: flevix
 * Date: 03.05.13
 * Time: 18:48
 */
public class Invoker {
//A test "aaa" "xxx" "vvv" "qqq"
//java Invoker java.util.HashMap put "XXX" "yyy"
//java Invoker java.util.Map put "key" "value"
//java Invoker java.util.Map2 put "key" "value"
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Invoker <full-class-name> <method-name> <method-arg...>");
            return;
        }
        final Class<?> cls = getClass(args[0]);
        if (!isGoodClass(cls)) {
            return;
        }

        final Object instance = getInstance(cls);
        if (instance == null) {
            return;
        }

        final String methodName = args[1];
        final int argsCount = args.length - 2;
        Object[] arguments = new Object[argsCount];
        System.arraycopy(args, 2, arguments, 0, argsCount);

        Set<List<Class<?>>> set = new HashSet<>();
        boolean isCorrectName = false;
        int count = 0;

        for (Class<?> cl = cls; cl != null; cl = cl.getSuperclass()) {
            boolean isParent = (cls != cl);

            for (Method m : cl.getMethods()) {
                if (m.getName().equals(methodName)) {
                    isCorrectName = true;
                    if (argsCount == m.getParameterTypes().length) {
                        if (!set.contains(Arrays.asList(m.getParameterTypes()))) {
                            if (isParent && Modifier.isPrivate(m.getModifiers())) {
                                continue;
                            }
                            set.add(Arrays.asList(m.getParameterTypes()));
                            count++;
                            try {
                                m.invoke(instance, arguments);
                                System.out.println(m + "\n" + instance);
                            } catch (InvocationTargetException e) {
                                System.err.println("Method threw an exception: " + e.getCause());
                            } catch (IllegalAccessException e) {
                                System.err.println("Method " + m.getName() + " is private");
                            } catch (IllegalArgumentException e) {
                                count--;
                            }
                        }
                    }
                }
            }
        }
        if (!isCorrectName) {
            System.err.println("No methods with such name");
        } else if (count == 0) {
            System.err.println("No methods with such arguments");
        }
    }

    private static boolean isGoodClass(Class<?> cls) {
        if (cls == null) {
            return false;
        } else if (cls.isAnnotation()) {
            System.err.println(cls.getName() + " is annotation");
            return false;
        } else if (cls.isInterface()) {
            System.err.println(cls.getName() + " is interface");
            return false;
        } else if (Modifier.isAbstract(cls.getModifiers())) {
            System.err.println(cls.getName() + " is abstract");
            return false;
        }
        return true;
    }

    private static Object getInstance(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            if (cls.isInterface()) {
                System.err.println(cls.getName() + " is interface");
            } else if (cls.isArray()) {
                System.err.println(cls.getName() + " is array");
            }  else if (cls.isPrimitive()) {
                System.err.println(cls.getName() + " is primitive type");
            }  else if (Modifier.isAbstract(cls.getModifiers())) {
                System.err.println(cls.getName() + " is abstract class");
            } else {
                System.err.println(cls.getName() + " has not nullable constructor");
            }
            return null;
        } catch (IllegalAccessException e) {
            System.err.println(cls.getName()
                    + " has not public default constructor");
            return null;
        }
    }

    private static Class<?> getClass(String name) {
        URL currentDir;
        try {
            currentDir = new File(".").getAbsoluteFile().toURI().toURL();
        } catch (MalformedURLException mue) {
            System.err.println(">:3\n" +
                    "RAWR I'M A LION!\n" +
                    "JESUS CHRIST IT'S A LION GET IN THE CAR!");
            return null;
        }
        ClassLoader loader = new URLClassLoader(new URL[]{currentDir});
        try {
            return loader.loadClass(name);
        } catch (ClassNotFoundException e) {
            System.err.println("Class " + name + " not found");
            return null;
        } catch (NoClassDefFoundError e) {
            System.err.println("Class " + name + " not available in runtime");
            return null;
        }
    }
}