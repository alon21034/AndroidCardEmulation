package tw.edu.ntu.alon;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

public class Reader {

	public Reader() {
		try {
			byte[] bytes = getCardUID();
			System.out.print(Utils.bytesToHex(bytes) + "");
		} catch (CardException e) {
			e.printStackTrace();
		}
	}

	public byte[] getCardUID() throws CardException {
		CardTerminals terminals = TerminalFactory.getDefault().terminals();
		CardTerminal terminal = terminals.list().get(0);
		Card card = terminal.connect("*");
		CardChannel channel = card.getBasicChannel();
		CommandAPDU command = new CommandAPDU(new byte[] { (byte) 0xFF,
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04,
				(byte) 0xD4, (byte) 0x4A, (byte) 0x01, (byte) 0x00 });
		ResponseAPDU response = channel.transmit(command);
		card.disconnect(true);
		return response.getData();
		
		// getSW1() == 0x90 means successful, comment it now.
		// if (response.getSW1() == 0x90) {
		// byte[] data = response.getData();
		// data = Arrays.copyOfRange(data, 0x08, data.length);
		// return data;
		// }
		// return new byte[] {};
	}
}
