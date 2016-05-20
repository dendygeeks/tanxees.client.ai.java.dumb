package dendygeeks.tanxees.client.ai.java.dumb;

import java.util.Random;

import dendygeeks.tanxees.api.java.model.PlayerKeysModel;
import dendygeeks.tanxees.api.java.model.TheStateModel;
import dendygeeks.tanxees.client.ai.java.AIBase;
import dendygeeks.tanxees.client.ai.java.ClientStateControllerBase;

public class Main extends AIBase {
	
	private static final long DECISION_TIME_DELTA = 1000;
	

	public static void main(String[] args) {
		Main main = new Main();
		
		if (args.length == 0) {
			System.err.println("Missing argument 'playerId'");
			return;
		}
		
		String playerId = args[0];
		
		main.connect(playerId, new ClientStateControllerBase() {
			
			private long clockValue = 0;
			
			private Random famousIntelligenceSource = new Random();
			
			@Override
			public boolean updateClientState(TheStateModel theState) {
				
				long newClockValue = System.currentTimeMillis();
				if (newClockValue - clockValue > DECISION_TIME_DELTA) {
					clockValue = newClockValue;
					
					PlayerKeysModel playerKeysModel = new PlayerKeysModel(
						famousIntelligenceSource.nextBoolean(),
						famousIntelligenceSource.nextBoolean(),
						famousIntelligenceSource.nextBoolean(),
						famousIntelligenceSource.nextBoolean(),
						famousIntelligenceSource.nextBoolean()
					);
					
					getClientStateModel().setKeys(playerKeysModel);
					
					return true;
				} else {
					return false;
				}
			}
		});
	}
}