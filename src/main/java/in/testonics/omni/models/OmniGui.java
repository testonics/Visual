package in.testonics.omni.models;

import in.testonics.omni.utils.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class OmniGui {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        final String[] absolutePathFile1 = {""};
        final String[] absolutePathFile2 = {""};

        // Create the main frame
        JFrame frame = new JFrame("CENT (Compare Extract Tool)");

        // Set the favicon
        Image icon = Toolkit.getDefaultToolkit().getImage("./docs/Logo.png"); // Replace with the actual path to your icon
        frame.setIconImage(icon);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(600, 400);
        frame.setLayout(new FlowLayout());

        // Create a JPanel to hold components
        JPanel panel = new JPanel();

        // Create components
        JButton browseButton1 = new JButton("Source File Or Folder");
        JButton browseButton2 = new JButton("Target File Or Folder");
        JButton submitButton = new JButton("Compare");
        // Create a JTextField with a default value
        // Create a JLabel for the edit box
        JLabel label = new JLabel("Report Location : ");
        panel.add(label);
        JTextField editBox = new JTextField(System.getProperty("user.dir"));
        panel.add(editBox);

        JTextArea outputTextArea = new JTextArea(20, 100);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEditable(false); // Make the text area read-only

        // Add action listeners for browse buttons
        browseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for browse button 1 action here
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Handle the selected file for button 1
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    // Add your logic here
                    outputTextArea.append("Source File : " + selectedFilePath + "\n");
                    absolutePathFile1[0] = selectedFilePath;
                }
            }
        });

        browseButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for browse button 2 action here
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Handle the selected file for button 2
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    // Add your logic here
                    outputTextArea.append("Target File : " + selectedFilePath + "\n");
                    absolutePathFile2[0] = selectedFilePath;
                }
            }
        });

        // Add action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for submit button action here
                // You can use the selected file paths from browse buttons
                outputTextArea.append("File Comparison Started ....\n");
                OmniFiles omniFiles = new OmniFiles();
                try {
                    String errors = omniFiles.CompareFiles(absolutePathFile1[0], absolutePathFile2[0],0).toString();
                    if (errors.equals("")){
                        outputTextArea.append("Compared files are identical. No mismatch found.\n");
                    } else {
                        outputTextArea.append("Mismatches found in the compared files...\n");
                        String logFile =  editBox.getText() + "\\" + new DateUtils().getTimeStamp() + ".log";
                        outputTextArea.append("Log file " + logFile + " generated with below mismatches found.\n");
                        // Create a FileWriter object
                        FileWriter writer = new FileWriter(logFile);
                        // Write the string to the file
                        writer.write(errors);
                        // Close the FileWriter to release resources
                        writer.close();
                        outputTextArea.append(errors);
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Add components to the frame
        frame.add(panel,BorderLayout.CENTER);
        frame.add(browseButton1,BorderLayout.CENTER);
        frame.add(browseButton2,BorderLayout.CENTER);
        frame.add(submitButton,BorderLayout.AFTER_LINE_ENDS);
        frame.add(new JScrollPane(outputTextArea),BorderLayout.CENTER); // Add a scroll pane for the text area

        // Set the frame visibility
        frame.setVisible(true);
    }
}
