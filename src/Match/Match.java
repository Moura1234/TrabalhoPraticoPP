package Match;

import Enums.Club;
import Enums.Formation;
import Enums.Team;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;

/**
 *
 * @author joaom
 */
public class Match implements IMatch, IClub, ITeam {
    
    private IClub HomeClub;
    private IClub AwayClub;
    private ITeam HomeTeam;
    private ITeam AwayTeam;
    private int HomeGoals;
    private int AwayGoals;
    private Formation HomeFormation;
    private Formation AwayFormation;

public Match (IClub HomeClub, IClub AwayClub, ITeam HomeTeam, ITeam AwayTeam, int HomeGoals, int AwayGoals, Formation HomeFormation, Formation AwayFormation) {
        this.HomeClub = HomeClub;
        this.AwayClub = AwayClub;
        this.HomeTeam = HomeTeam;
        this.AwayTeam = AwayTeam;
        this.HomeGoals = HomeGoals;
        this.AwayGoals = AwayGoals;
        this.HomeFormation = HomeFormation;
        this.AwayFormation = AwayFormation;
}


    @Override
    
    public IClub getHomeClub() {
        return this.HomeClub;
    }

    @Override
    public IClub getAwayClub() {
        return this.AwayClub;
    }


    @Override
   public ITeam getHomeTeam() {
       
   return this.HomeTeam;

}

    @Override
    public ITeam getAwayTeam(){
        return this.AwayTeam;
    }

    public int getHomeGoals() {
        return this.HomeGoals;
    }

    public int getAwayGoals() {
        return this.AwayGoals;
    }

    public Formation getHomeFormation() {
        return this.HomeFormation;
    }

    public Formation getAwayFormation() {
        return this.AwayFormation;
    }
}
