package utils;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import core.CodeFactory;
import core.CodeKtFactory;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtClass;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.KtModifierList;

import java.util.Collection;

public class PluginUtils {

    /**
     * 获取Java文件的Class类对象
     */
    public static PsiClass getFileClass(PsiFile file) {
        for (PsiElement psiElement : file.getChildren()) {
            if (psiElement instanceof PsiClass) {
                return (PsiClass) psiElement;
            }
        }
        return null;
    }

    public static KtClass getKtFileClass(PsiFile file) {

        Collection<KtClass> ktClasses = PsiTreeUtil.findChildrenOfType(file, KtClass.class);
        if (!ktClasses.isEmpty()){
            return ktClasses.iterator().next();
        }
        return null;
    }


    public static void addMethodForFile(Project project , VirtualFile virtualFile){

        if (virtualFile.isDirectory()){

            System.out.println(" isDirectory   ->" + virtualFile.getName());

            for (VirtualFile vfile : virtualFile.getChildren()){
                addMethodForFile(project, vfile);
            }

        }else {

            // 识别 java 和 ktClass 抽象类考虑不添加方法
            PsiFile pFile = PsiManager.getInstance(project).findFile(virtualFile);
            System.out.println(" pFile   ->" + pFile.getName());

            if (pFile instanceof KtFile){

                KtClass ktClass = PluginUtils.getKtFileClass(pFile);
                if (ktClass != null && !(ktClass.isData() || ktClass.isEnum() || ktClass.isInterface())){

                    KtModifierList modifierList = ktClass.getModifierList();
                    if (modifierList == null || !modifierList.hasModifier(KtTokens.ABSTRACT_KEYWORD)){
                        System.out.println(" ktClass   ->" + ktClass.getName());
                        CodeKtFactory.addMethod(project,ktClass,0.5);
                    }
                }

            }else{

                PsiClass psiClass = PluginUtils.getFileClass(pFile);
                if (psiClass != null && !(psiClass.isEnum() || psiClass.isInterface())){

                    PsiModifierList modifierList = psiClass.getModifierList();

                    if (modifierList == null ||  !modifierList.hasModifierProperty("abstract")){
                        System.out.println(" PsiClass   ->" + psiClass.getName());
                        CodeFactory.addMethod(project,psiClass,0.5);
                    }
                }

            }
        }
    }
}
