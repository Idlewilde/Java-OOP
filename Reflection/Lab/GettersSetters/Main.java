import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class reflection = Reflection.class;
         Method[] methods = reflection.getDeclaredMethods();

         Method[] getters = Arrays.stream(methods)
                 .filter(e->e.getName().startsWith("get")&&e.getParameterCount()==0)
                 .sorted(Comparator.comparing(Method::getName)).toArray(Method[]::new);

        Method[] setters = Arrays.stream(methods)
                .filter(e->e.getName().startsWith("set")&&e.getParameterCount()==1)
                .sorted(Comparator.comparing(Method::getName)).toArray(Method[]::new);

        Arrays.stream(getters)
                .forEach(e-> System.out.println(e.getName()+" will return class "+e.getReturnType().getName()));
        Arrays.stream(setters)
                .forEach(e-> System.out.println(e.getName()+" and will set field of class "+e.getParameterTypes()[0].getName()));



}}
