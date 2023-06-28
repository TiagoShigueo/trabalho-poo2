package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ClienteView;
import view.ContaView;

public class MenuInicial extends JFrame {
    private JButton clienteButton;
    private JButton contaButton;

    public MenuInicial() {
        setTitle("Menu Inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        clienteButton = new JButton("Clientes");
        contaButton = new JButton("Contas");

        clienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClienteView clienteView = new ClienteView();
                //clienteView.setVisible(true);
            }
        });

        contaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ContaView contaView = new ContaView();
                contaView.setVisible(true);
            }
        });

        setLayout(new FlowLayout());
        add(clienteButton);
        add(contaButton);
    }
}

