package view;

import controller.SubmitHostListener;
import controller.HostValidationListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import static java.awt.Color.RED;
import static java.awt.GridBagConstraints.HORIZONTAL;

public class HostDialog {
	private MainWindow mainWindow;
	private JDialog dialog;
	private JTextField textField;
	private JButton button;
	private HostValidationListener validator;
	private JLabel warning;
	private JLabel label;

	public HostDialog(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				build();
			}
		});
	}

	void build() {
		dialog = new JDialog(mainWindow);
		textField = new JTextField();
		button = new JButton("Continue");
		validator = new HostValidationListener(this);
		warning = new JLabel("");
		label = new JLabel("Please enter port for host connection");
		warning.setVisible(false);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = HORIZONTAL;

		dialog.setTitle("Host");
		dialog.setLayout(new GridBagLayout());
		dialog.setResizable(false);
		dialog.setModal(true);

		warning.setForeground(RED);
		button.addActionListener(new SubmitHostListener(mainWindow, this));
		textField.setColumns(4);
		textField.getDocument().addDocumentListener(validator);

		dialog.add(label, constraints);

		constraints.gridx = 1;
		dialog.add(textField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		dialog.add(button, constraints);

		constraints.gridy = 2;
		dialog.add(warning, constraints);

		dialog.setPreferredSize(new Dimension(350, 150));
		dialog.pack();
		dialog.setLocationRelativeTo(mainWindow);
		dialog.setVisible(true);
	}

	public String getPortText() {
		return (textField.getText().trim());
	}

	public HostValidationListener getTextFieldValidator() {
		return (validator);
	}

	public JDialog getDialog() {
		return (dialog);
	}

	public void showWarningLabel(String message) {
		warning.setText(message);
		warning.setVisible(true);
		dialog.revalidate();
		dialog.repaint();
	}

	public void hideWarningLabel() {
		warning.setVisible(false);
		dialog.revalidate();
		dialog.repaint();
	}
}
