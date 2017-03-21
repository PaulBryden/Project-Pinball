package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.IAbsorber;
import model.IBall;
import model.IGizmo;
import physics.Vect;

public class SelectSidePanel extends SidePanel {

	private static final long serialVersionUID = 2892927258047811490L;

	public SelectSidePanel() {
		super("Select a gizmo to view or edit its properties.");
	}

	public SelectSidePanel(IGizmo gizmo, MainWindow mainWindow) {
		super();
		List<JComponent> components = new ArrayList<>();
		JLabel typeLabel = new JLabel(gizmo.getClass().getSimpleName());
		Font font = typeLabel.getFont().deriveFont(Font.PLAIN);
		typeLabel.setFont(font);
		components.add(new JLabel("Type:"));
		components.add(typeLabel);
		JLabel coordsLabel = new JLabel(coordsString(gizmo.getGridCoords()));
		coordsLabel.setFont(font);
		components.add(new JLabel("Position:"));
		components.add(coordsLabel);
		JLabel idLabel = new JLabel(gizmo.getID());
		idLabel.setFont(font);
		components.add(new JLabel("ID:"));
		components.add(idLabel);
		Color colour = gizmo.getColour();
		JButton colourButton = ButtonFactory.createColourButton(colour, "gizmo_colour", "Gizmo colour", new ColourActionListener(mainWindow, gizmo));
		components.add(new JLabel("Colour:"));
		components.add(colourButton);
		if (gizmo instanceof IBall) {
			IBall ball = (IBall) gizmo;
			components.add(new JLabel("X-velocity:"));
			SpinnerModel model = new SpinnerNumberModel(ball.getVelo().x(), -50, 50, 0.01);
			JSpinner x_spinner = new JSpinner(model);
			components.add(x_spinner);
			components.add(new JLabel("Y-velocity:"));
			model = new SpinnerNumberModel(ball.getVelo().y(), -50, 50, 0.01);
			JSpinner y_spinner = new JSpinner(model);
			components.add(y_spinner);
			x_spinner.addChangeListener(new VeloSpinnerListener(ball, x_spinner, y_spinner));
			y_spinner.addChangeListener(new VeloSpinnerListener(ball, x_spinner, y_spinner));
		} else if (!(gizmo instanceof IAbsorber)) {
			components.add(new JLabel("COR:"));
			SpinnerModel model = new SpinnerNumberModel(gizmo.getCoefficientOfReflection(), 0, 2, 0.01);
			JSpinner cor_spinner = new JSpinner(model);
			components.add(cor_spinner);
			cor_spinner.setToolTipText("Set coefficient of reflection");
			cor_spinner.addChangeListener(new CORSpinnerListener(gizmo));
		}
		JPanel panel = createTitledPanel("Gizmo properties", 2, components.toArray(new JComponent[components.size()]));
		build(panel);
	}

	private String coordsString(Vect coords) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(coords.x()) + ", " + df.format(coords.y());
	}
	
	class ColourActionListener implements ActionListener {
		
		private IGizmo gizmo;
		private MainWindow mainWindow;
		
		public ColourActionListener(MainWindow mainWindow, IGizmo gizmo) {
			this.gizmo = gizmo;
			this.mainWindow = mainWindow;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color colour = JColorChooser.showDialog(mainWindow, "Choose gizmo colour", gizmo.getColour());
			gizmo.setColour(colour);
			mainWindow.getBoard().reRender();
			JButton button = (JButton) e.getSource();
			button.setBackground(colour);
		}
		
	}
	
	class VeloSpinnerListener implements ChangeListener {
		
		private IBall ball;
		private JSpinner xSpinner;
		private JSpinner ySpinner;
		
		public VeloSpinnerListener(IBall ball, JSpinner xSpinner, JSpinner ySpinner) {
			this.ball = ball;
			this.xSpinner = xSpinner;
			this.ySpinner = ySpinner;
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			ball.setVelo(new Vect((double) xSpinner.getValue(), (double) ySpinner.getValue()));
		}
		
	}

	class CORSpinnerListener implements ChangeListener {
		
		private IGizmo gizmo;
		
		public CORSpinnerListener(IGizmo gizmo) {
			this.gizmo = gizmo;
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			JSpinner spinner = (JSpinner) e.getSource();
			double value = (double) spinner.getValue();
			gizmo.setCoefficientOfReflection(value);
		}
		
	}
}
