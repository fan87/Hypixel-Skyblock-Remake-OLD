import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        for (Field declaredField : fuck.class.getDeclaredFields()) {
            System.out.println(declaredField);
        }
        for (Method declaredMethod : fuck.class.getDeclaredMethods()) {
            System.out.println(declaredMethod);
        }
        String x = "fehufi";
        int y = 4;
        fuck.class.getDeclaredMethod("hi", String.class).invoke(new fuck(0), x);
        Method penis = fuck.class.getDeclaredMethod("penis", int.class);
        penis.setAccessible(true);
        penis.invoke(null,  y);
        Field isThatTrue = fuck.class.getDeclaredField("isThatTrue");
        isThatTrue.setAccessible(true);
        isThatTrue.setBoolean(null, true);
        System.out.println(isThatTrue.getBoolean(null));
        fuck fuck = fuck.class.getConstructor(int.class).newInstance(0);
        System.out.println(fuck.fuck);

    }
}
