package core;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.psi.KtClass;
import org.jetbrains.kotlin.psi.KtFile;
import utils.PluginUtils;

public class AssociateAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {

        Project project = e.getData(PlatformDataKeys.PROJECT);
        VirtualFile virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);

        if (project != null && virtualFile != null){
            PsiFile pFile = PsiManager.getInstance(project).findFile(virtualFile);
            PsiClass psiClass = PluginUtils.getFileClass(pFile);
            CodeFactory.invokeMethod(project,psiClass);

//            if (pFile instanceof KtFile) {
//
//                KtClass ktClass = PluginUtils.getKtFileClass(pFile);
//                CodeKtFactory.invokeMethod(project, ktClass);
//            }


        }


    }
}
