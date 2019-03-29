package core;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import utils.PluginUtils;

public class InsertAction extends AnAction {

    // 一个模块中有多个 文件夹
    // 选择模块
    // 找到需要添加无用代码的文件
    // 找到 class
    // 内部 关联代码
    // 相互调用


    // 需要一个class 来生成目标格式的classname/判断是不是想要的方法名
    // 方法提供类


    // 拿到目录中的所有文件，通过界面来决定对哪些类进行处理（）。还得区分java 和 kotlin .注入方法的方法


//        PsiFile file = e.getData(PlatformDataKeys.PSI_FILE);
//        for (PsiElement psiElement : file.getChildren()) {
//            System.out.println(psiElement);
//        }



    // kotlin 支持，文件选择


    // 给 Java 文件添加代码
    // 给 kotlin 文件添加 methods



    @Override
    public void actionPerformed(AnActionEvent e) {

        Project project = e.getData(PlatformDataKeys.PROJECT);
        VirtualFile virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);

        System.out.println( "PsiPackage ->" + virtualFile.getName());
        PluginUtils.addMethodForFile(project, virtualFile);
    }
}






/*
*
* List<KtProperty> properties = ktClass.getProperties();

                    for (KtProperty pro : properties) {


                        List<KtTypeConstraint> parameters = pro.getTypeConstraints();
                        for (KtTypeConstraint param : parameters) {

                            System.out.println("    -> " + param.getName() + " --" + param.getText());
                        }
                        System.out.println("    end ");


                        try {
                            Class targetC = Class.forName("myapp.com.example.news365_macpro.myapp.Student");
                            if (pro.getClass().isAssignableFrom(targetC)){

                                System.out.println("能转成目标格式");
                            }


                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    }
*
* */


//    PsiField[] fields = fileClass.getAllFields();
//                System.out.println(" Java   ->" + pFile.getName());
//                        for (PsiField field : fields){
//
//                        field.get
//                        System.out.println("    ->" + field.getType().getCanonicalText());
//
//                        }
//
//                        return;


//            for (PsiElement psiElement : pFile.getChildren()) {
//                if (psiElement instanceof PsiClass) {
//                    return (PsiClass) psiElement;
//                }
//            }
//
//            Collection<KtClass> ktClasses = PsiTreeUtil.findChildrenOfType(pFile, KtClass.class);
//            for (KtClass kt : ktClasses) {
//                System.out.println("Kotlin class ->" + kt.getName());
//
//
//                PsiElement[] elements = kt.getBody().getChildren();
//
//                CodeKtFactory.addMethod(project,kt,1);
//
//
////                List<KtDeclaration> ps = kt.getDeclarations();
//                for (PsiElement p : elements){
//                    System.out.println("    ->" + p.getText());
//                }
//
//
//            }

// 核心代码。
//


//        Module[] modules = ModuleManager.getInstance(project).getModules();
//        Module m = modules[1];
//
//
//        VirtualFile[] roots = ModuleRootManager.getInstance(m).orderEntries().classes().getRoots();
//        for (VirtualFile file : roots){
//            System.out.println(file.getName());
//        }


//        PsiFile file = e.getData(PlatformDataKeys.PSI_FILE);
//        for (PsiElement psiElement : file.getChildren()) {
//            if(psiElement instanceof PsiClass) {
//                PsiClass clazz = (PsiClass) psiElement;
//                System.out.println(clazz.getName());
//                PsiMethod method = clazz.getMethods()[0];
//                System.out.println(method.getName());
//                System.out.println(method.getParameterList().getText());
//            }
//        }