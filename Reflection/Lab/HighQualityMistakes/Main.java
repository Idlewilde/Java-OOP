import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class reflection = Reflection.class;
         Method[] methods = reflection.getDeclaredMethods();
         int modifiers = reflection.getModifiers();

         Method[] getters = Arrays.stream(methods)
                 .filter(e->e.getName().startsWith("get")&&e.getParameterCount()==0)
                 .sorted(Comparator.comparing(Method::getName)).toArray(Method[]::new);

        Method[] setters = Arrays.stream(methods)
                .filter(e->e.getName().startsWith("set")&&e.getParameterCount()==1)
                .sorted(Comparator.comparing(Method::getName)).toArray(Method[]::new);

        Field[] fields = Arrays.stream(reflection.getDeclaredFields())
                .sorted(Comparator.comparing(Field::getName)).toArray(Field[]::new);

        for (Field field : fields) {
            if(!Modifier.isPrivate(field.getModifiers())){
                System.out.println(field.getName()+" must be private!");

        }}

        for (Method getter : getters) {
            if(!Modifier.isPublic(getter.getModifiers())){
                System.out.println(getter.getName()+" have to be public!");
        }}

        for (Method setter : setters) {
            if(!Modifier.isPrivate(setter.getModifiers())){
                System.out.println(setter.getName()+" have to be private!");
            }
        }



}}
