package komServ;

import java.util.LinkedList;
import java.util.List;

import logika.Strategija;
import logika.Swot;
import logika.SwotStrat;

public class KomunikacijaSaKlijentima {

	public static void prebaciPodatkeUListu(String poruka, List<Strategija> lista) throws Exception{
		if(poruka == null || poruka.isEmpty())
			return;
		System.out.println(poruka);
		String[] vrednosti = poruka.split("\n");
		String[] brojevi;
		
		for(int i=0; i<vrednosti.length; i++){
			Strategija strat = lista.get(i);
			List<SwotStrat> snage = strat.getSnage();
			List<SwotStrat> slabosti = strat.getSlabosti();
			List<SwotStrat> sanse = strat.getSanse();
			List<SwotStrat> pretnje = strat.getPretnje();
			brojevi = vrednosti[i].split(" ");
			int j = 0;
			int sns = snage.size();
			int sls = slabosti.size();
			int sans = sanse.size();
			int prs = pretnje.size();
			for(; j<sns; j++){
				snage.get(j).setAtraktivnost(snage.get(j).getAtraktivnost() + Double.parseDouble(brojevi[j]));
			}
			for(; j<sns+sls; j++){
				slabosti.get(j-sns).setAtraktivnost(slabosti.get(j-sns).getAtraktivnost() + Double.parseDouble(brojevi[j-sns]));
			}
			for(; j<sns+sls+sans; j++){
				sanse.get(j-sns-sls).setAtraktivnost(sanse.get(j-sns-sls).getAtraktivnost() + Double.parseDouble(brojevi[j-sns-sls]));
			}
			for(; j<sns+sls+sans+prs; j++){
				pretnje.get(j-sns-sls-sans).setAtraktivnost(pretnje.get(j-sns-sls-sans).getAtraktivnost() + Double.parseDouble(brojevi[j-sns-sls-sans]));
			}
		}
	}
	
	public static String prebaciPodatkeUString(List<Strategija> strategije){
		String str = "";
		
		for(Strategija s : strategije){
			str = str + s.getNaziv() + "\t";
			
			if(s.getSnage().isEmpty())
				str += "nema\t";
			else{
				for(SwotStrat swot : s.getSnage()){
					str = str + swot.getNaziv() + " " + swot.getPonder() + " ";
				}
				str = str.trim() + "\t";
			}
			
			if(s.getSlabosti().isEmpty())
				str += "nema\t";
			else{
				for(SwotStrat swot : s.getSlabosti()){
					str = str + swot.getNaziv() + " " + swot.getPonder() + " ";
				}
				str = str.trim() + "\t";
			}
			if(s.getSanse().isEmpty())
				str += "nema\t";
			else{
				for(SwotStrat swot : s.getSanse()){
					str = str + swot.getNaziv() + " " + swot.getPonder() + " ";
				}
				str = str.trim() + "\t";
			}
			
			if(s.getPretnje().isEmpty())
				str += "nema\n";
			else{
				for(SwotStrat swot : s.getPretnje()){
					str = str + swot.getNaziv() + " " + swot.getPonder() + " ";
				}
			
				str = str + "\n";
			}
		}
			 
		return str;
	}
	
	public static void sumirajPodatke(List<Strategija> lista, int broj){
		if(lista == null || broj < 1)
			return;
		
		for(Strategija s : lista){
			for(SwotStrat snaga : s.getSnage()){
				snaga.setAtraktivnost(snaga.getAtraktivnost()/broj);
			}
			for(SwotStrat slabost : s.getSlabosti()){
				slabost.setAtraktivnost(slabost.getAtraktivnost()/broj);
			}
			for(SwotStrat sansa : s.getSanse()){
				sansa.setAtraktivnost(sansa.getAtraktivnost()/broj);
			}
			for(SwotStrat pretnja : s.getPretnje()){
				pretnja.setAtraktivnost(pretnja.getAtraktivnost()/broj);
			}
			s.izracunajSumuUkupnihAtraktivnosti();
		}
	}
}
