package Commands;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;

public class Help implements Command {
    public String man = "Prints this list";
    public void execute(Game.Engine game, String[] args) {
        // The directory containing the .class files
        File dir = new File(System.getProperty("user.dir")); // Commands Directory 

        //System.out.println(dir);
        
        // URLClassLoader to dynamically load classes
        try {
            System.out.println("------------------------");
            URLClassLoader classLoader = new URLClassLoader(new URL[] { dir.toURI().toURL() });
            //Files.walk(dir.toPath()).forEach(path -> System.out.println(path));
            
            // Get all .class files in the directory
            Files.list(Paths.get(System.getProperty("user.dir"), "Commands"))
                .filter(path -> path.toString().endsWith(".class"))
                .filter(path -> !path.toFile().getName().replace(".class", "").equals("Command"))
                .forEach(path -> {
                    try {
                        String className = "Commands." + path.toFile().getName().replace(".class", "");
                        // Dynamically load the class
                        Class<?> clazz = classLoader.loadClass(className);
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        // Use reflection to access the "man" variable
                        Field manField = clazz.getDeclaredField("man");
                        manField.setAccessible(true);
                        String manDescription = (String) manField.get(instance); // Get field

                        // Print the command (class name) and its description
                        int index = className.indexOf('.');

                        // Capitalize the first letter
                        String classString = className.substring(index + 1).toLowerCase();
                        String firstChar = (classString.charAt(0) + "").toUpperCase();
                        classString = firstChar + classString.substring(1, classString.length()); 

                        System.out.println(
                            classString + 
                            ": " + manDescription
                        );
                        System.out.println("------------------------");
                    } catch (Exception e) {
                        //System.err.println("Error processing class: " + className);
                        //e.printStackTrace();
                        
                        // If it doesn't work this command can shut it.
                    }
                });


            classLoader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}