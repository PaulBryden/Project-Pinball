package view;

import java.awt.Graphics;
import java.util.Observer;

import javax.swing.JPanel;

public interface IViewGizmo extends Observer{


	Graphics GetViewObject();
}
