<img src="./platform.png" alt="Platform" width="300"/>

# Platform Backend
Platform Backend provides an easy way to fetch information about the current status of Network Rail Trains and Stations.

## Installation and Usage
1. Get API Keys from the [Rail Data Marketplace](https://raildata.org.uk/) (RDM).
Once you have been approved for an RDM account, you will need to subscribe to these services:

   - Live Arrival Board
   - Live Departure Board
   - Live Arrival and Departure Boards
   - Live Fastest Departures Board
   - Live Next Departures Board
   - Service Details

	Once you have subscribed and been approved for these services, you can find your API Keys in the Specification tab on
	the service page.

2. Download the Latest JAR from the [Releases Page](https://github.com/Lythium4848/Platform-Backend/releases) - 
Platform-Backend runs on  Java 21 so ensure you have it installed on your system.
3. Create a `.env` file based on the `.env.example` file in the same directory as the Platform-Backend JAR. The API Keys
   you just got from RDM should then be put into the `.env` file.
4. Run the JAR with `java -jar Platform-Backend.jar`
5. The API will be available at `http://localhost:<PORT>/` where `<PORT>` is the port specified in your `.env` file.