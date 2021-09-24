package ssafy;

import org.apache.hadoop.util.ProgramDriver;

public class Driver {
	public static void main(String[] args) {
		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {

			pgd.addClass("cctv", CCTVCountByHJD.class, "count how many cctvs there are by region");
			pgd.addClass("police", policeCountByHJD.class, "count how many police offices there are by region");
			pgd.addClass("bar", barCountByHJD.class, "count how many bars there are by region");
			pgd.addClass("boanCount", boanCountByHJD.class, "boanCount");
			pgd.addClass("GuardHouseCountByHJD", GuardHouseCountByHJD.class, "GuardHouse");
      			pgd.driver(args);
			exitCode = 0;
		}
		catch(Throwable e) {
			e.printStackTrace();
		}

		System.exit(exitCode);
	}
}
