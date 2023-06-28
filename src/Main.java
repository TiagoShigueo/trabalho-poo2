import javax.swing.SwingUtilities;

import view.ClienteView;
import view.ContaView;
import view.MenuInicial;
public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuInicial menuInicial = new MenuInicial();
                menuInicial.setVisible(true);
            }
        });
		//ContaView contaView = new ContaView();
		//System.out.println("Passou da ContaView");
		//ClienteView clienteView = new ClienteView();
	}
}
