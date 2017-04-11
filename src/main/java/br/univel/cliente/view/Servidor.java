package br.univel.cliente.view;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.univel.jshare.comum.Arquivo;
import br.univel.jshare.comum.Cliente;
import br.univel.jshare.comum.IServer;
import br.univel.jshare.comum.TipoFiltro;

public class Servidor implements Runnable, IServer {

	private static IServer meuServidor;
	private final String ipServidor;
	private final Integer portaServidor;

	private Thread threadMonitor;
	private static Registry registry;

	private Map<Cliente, List<Arquivo>> mapaArquivos = new HashMap<Cliente, List<Arquivo>>();
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private List<Arquivo> listaArquivos = new ArrayList<Arquivo>();

	public Servidor(String meuIp, Integer porta) {
		this.ipServidor = meuIp;
		this.portaServidor = porta;

		PanelServidor.getTextArea().append("Iniciando Servidor\n");
		this.threadMonitor = new Thread(this);
		this.threadMonitor.start();
	}

	public static boolean desligar() {
		try {
			UnicastRemoteObject.unexportObject(registry, true);
			registry = null;
			meuServidor = null;

			PanelServidor.getTextArea().append("Servidor Finali!\n");

			return true;
		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void run() {
		try {
			meuServidor = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(portaServidor);
			registry.rebind(IServer.NOME_SERVICO, meuServidor);
			PanelServidor.getTextArea().append("Servidor Iniciado!\n");
		} catch (RemoteException e) {
			PanelServidor.getTextArea().append("Erro ao iniciar servidor: \n" + e.getMessage());
		}
	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		if (!listaClientes.contains(c)) {
			listaClientes.add(c);
		}
		PanelServidor.getTextArea().append(String.format("Cliente %s se conectou!\n", c.getNome()));
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		mapaArquivos.put(c, lista);
		lista.forEach(a -> {
			System.out.println(c.getNome() + " " + a.getNome());
		});
		PanelServidor.getTextArea().append(String.format("Cliente %s publicou sua lista de arquivos!\n", c.getNome()));
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String query, TipoFiltro tipoFiltro, String filtro)
			throws RemoteException {
		Map<Cliente, List<Arquivo>> mapaAux = new HashMap<>();
		
		Pattern pattern = Pattern.compile(".*" + query + ".*");
		
		mapaArquivos.entrySet().forEach(cliente -> {
			mapaArquivos.get(cliente).forEach(arquivos -> {				
				Matcher matcher = pattern.matcher(arquivos.getNome());
				if (matcher.matches()) {
					mapaAux.put((Cliente) mapaArquivos.entrySet(), mapaArquivos.get(mapaArquivos.entrySet()));
				}
			});
		});

		PanelServidor.getTextArea().append(String.format("Cliente buscou por %s.\n", query));
		return mapaAux;
	}

	@Override
	public byte[] baixarArquivo(Cliente cli, Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {

		PanelServidor.getTextArea().append(String.format("Cliente %s se desconectou!\n", c.getNome()));
	}

}
