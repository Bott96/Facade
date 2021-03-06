package ingsw.patterns.Facade;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Prova extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p;
	private JButton plsCercaViaggio;
	private JTextField txtCittaPartenza, txtCittaArrivo, txtNomeH, txtRisVolo;
	private JList<Hotel> lstHotel, lstRis;
	private JList<Volo> lstVoli;
	Vector<Hotel> ris;

	public Prova() {
		super();

		p = new JPanel(null);

		this.setTitle("Checcah");

		this.setLocation(300, 300);
		Color c = new Color(10, 100, 100);

		Toolkit TK = Toolkit.getDefaultToolkit();

		Dimension d = TK.getScreenSize();
		this.setSize(d.width / 2, d.height / 2);

		plsCercaViaggio = new JButton("CercaViaggio");
		txtCittaArrivo = new JTextField(40);
		txtCittaPartenza = new JTextField(40);
		txtRisVolo = new JTextField(40);
		txtNomeH = new JTextField(40);
		plsCercaViaggio.setBounds(d.height / 2, 10, 110, 30);
		txtCittaArrivo.setBounds(d.height / 2, 80, 200, 20);
		txtCittaPartenza.setBounds(d.height / 2, 50, 200, 20);
		txtRisVolo.setBounds(d.height / 2, 110, 200, 20);
		txtRisVolo.setEnabled(false);
		// txtNomeH.setBounds(d.height / 2, 140, 200, 20);

		FacadeJurney facade = new FacadeJurney();
		Vector<Hotel> v1 = facade.getAviableHotel();
		Vector<Volo> v2 = facade.getAviableFlight();

		lstVoli = new JList<Volo>(v2);
		lstVoli.setBounds(300, 50, 150, 350);
		lstVoli.updateUI();

		lstHotel = new JList<Hotel>(v1);
		lstHotel.setBounds(50, 50, 150, 350);
		lstHotel.updateUI();

		lstVoli.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String s= lstVoli.getSelectedValue().toString();
					txtCittaPartenza.setText(s.substring(0, s.indexOf('-')));
					txtCittaArrivo.setText(s.substring(s.indexOf('>')+1));
				}
			}
		});

		ris = new Vector<>();
		lstRis = new JList<Hotel>(ris);
		lstRis.setBounds(d.height / 2, 140, 200, 100);
		;
		lstRis.updateUI();

		plsCercaViaggio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtRisVolo.setText("");
				ris.clear();
				Volo vo = new Volo();

				facade.getHotelAndVoloByLocation(txtCittaPartenza.getText(), txtCittaArrivo.getText(), vo, ris);
				txtRisVolo.setText(vo.toString());

				lstRis.updateUI();
			}
		});

		// lstHotel.

		txtCittaPartenza.setText("Lamezia");
		txtCittaArrivo.setText("Roma");
		txtNomeH.setText("Hotel SPQR");
		p.add(plsCercaViaggio);

		p.add(txtCittaPartenza);
		p.add(txtCittaArrivo);
		p.add(lstRis);
		p.add(lstHotel);
		p.add(txtRisVolo);
		p.add(lstVoli);
		this.setBackground(c);
		this.setContentPane(p);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		Prova p = new Prova();
	}

}
