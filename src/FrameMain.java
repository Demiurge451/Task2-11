import util.ArrayUtils;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Comparator;
import javax.swing.filechooser.FileFilter;

public class FrameMain extends  JFrame{
    private JPanel panelMain;
    private JTextArea textAreaInput;
    private JTextArea textAreaOutput;
    private JButton buttonLoadInput;
    private JButton buttonExecute;
    private JButton buttonSaveOutput;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBar;
    private JMenu menuLookAndFeel;


    public FrameMain(){
        this.setTitle("Task2");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");
        textAreaInput.setText("1 2 -3 -4 -6");

        this.pack();


        buttonLoadInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        textAreaInput.setText(SimpleLinkedList.readLinkedListFromFile(fileChooserOpen.getSelectedFile().getPath()));
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonSaveOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] arr = new int[1];
                        arr[0] = Integer.valueOf(textAreaOutput.getText());
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
                    String[] arr = textAreaInput.getText().split(" ");
                    for (String s : arr) {
                        list.addLast(Integer.parseInt(String.valueOf(s)));
                    }
                    textAreaOutput.setText(String.valueOf(list.func(Integer::compareTo, 0)));
                }catch (Exception er){
                    textAreaOutput.setText("Некорректные данные");
                }

            }
        });

    }
}
