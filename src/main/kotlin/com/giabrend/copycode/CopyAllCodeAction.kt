package com.giabrend.copycode

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.ui.Messages
import java.awt.datatransfer.StringSelection

/**
 * An IntelliJ IDEA plugin action that copies the entire content of the currently active editor
 * to the clipboard. This action is accessible via both the context menu and the toolbar button.
 */
class CopyAllCodeAction : AnAction() {

    /**
     * Handles the action when triggered.
     * - Checks for the currently active editor.
     * - Copies all the content of the file being edited to the clipboard.
     * - Displays a success message or an error message if no editor is found.
     */
    override fun actionPerformed(event: AnActionEvent) {
        // Get the currently active editor
        val editor: Editor? = EditorFactory.getInstance().allEditors.firstOrNull()

        // Check if an editor is active
        if (editor == null || editor.document == null) {
            // Show an error message if no editor is active
            Messages.showErrorDialog("No active editor found.", "Error")
            return
        }

        // Get the content of the current document
        val fileContent = editor.document.text

        // Copy the content to the clipboard
        CopyPasteManager.getInstance().setContents(StringSelection(fileContent))

        // Show a success message to the user
        Messages.showInfoMessage("Code copied to clipboard!", "Success")
    }
}
