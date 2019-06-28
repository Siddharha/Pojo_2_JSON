package sample;

import javax.print.DocFlavor;
import javax.tools.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AppUtil {
    private static final String CLASS_FOLDER =
            "/generated/";
   /* String JsonFromPojo(String strClass){
        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() { // set config option by overriding method
                return true;
            }
        };

        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(codeModel, "ClassName", "com.example", source);

        codeModel.build(Files.createTempDirectory("required").toFile());
        return veriableNameList;
    }*/

    Object createDynamicObj(String s) throws IOException, NoSuchMethodException, InvocationTargetException {

        File sourceFile = new File("src/sample/G.java");
        FileWriter writer = new FileWriter(sourceFile);
        writer.write("package sample;\n"+s);
        writer.close();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(
                null, null, null);

        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays
                .asList(new File("src/sample")));
        // Compile the file
        boolean success = compiler.getTask(null, fileManager, null, null, null,
                fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile)))
                .call();
        fileManager.close();
        //runIt();
        //loadClassObj();
        try {
            return DoProcess(sourceFile);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void runIt() {
        try {
            Class params[] = {};
            Object paramsObj[] = {};
            Class thisClass = getClassByName("");// Class.forName("MyApp");
            Object iClass = thisClass.newInstance();
            // Method thisMethod = thisClass.getDeclaredMethod("doit", params);
            //thisMethod.invoke(iClass, paramsObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Class<?> getClassByName(String name) {
        try {
            // Retrieve class by name.
            return Class.forName(name);
        } catch (ClassNotFoundException exception) {
            // Output exception ClassNotFoundExceptions.
            System.out.println(exception);
            // Logging.log(exception);
        } catch (Exception exception) {
            // Output unexpected Exceptions.
            // Logging.log(exception, false);
            System.out.println(exception);
        }
        return null;
    }

    Object DoProcess(File sourceFile) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

            System.out.println("Yipe");
            // Create a new custom class loader, pointing to the directory that contains the compiled
            // classes, this should point to the top of the package structure!
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File("./").toURI().toURL()});
            // Load the class from the classloader by name....
            Class<?> loadedClass = classLoader.loadClass("sample.G");
            // Create a new instance...
            Object obj = loadedClass.getDeclaredConstructor().newInstance();

            return obj;

            // Santity check


    }

 }

