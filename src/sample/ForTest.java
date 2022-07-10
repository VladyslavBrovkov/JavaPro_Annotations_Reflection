package sample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ForTest {

    @Test
    public void test(int a, int b) {
        System.out.println("Annotation parameters print through method: a=" + a + " b=" + b);
    }

}

class HomeworkL3 {

    public static void main(String[] args) {
        try {
            printAB();
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException");
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }

    }

    public static void printAB() throws InvocationTargetException, IllegalAccessException {
        final Class<?> cls = ForTest.class;
        Method[] methods = cls.getMethods();
        for (Method met : methods) {
            if (met.isAnnotationPresent(Test.class)) {
                Test ts = met.getAnnotation(Test.class);
                int a = ts.param1();
                int b = ts.param2();
                ForTest ft = new ForTest();
                met.invoke(ft, a, b);
            }
        }
    }
}

