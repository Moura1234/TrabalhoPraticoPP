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
public class Match implements IMatch {
    
    private IClub homeClub;
    private IClub awayClub;
    private ITeam homeTeam;
    private ITeam awayTeam;
    private int homeGoals;
    private int awayGoals;
    private Formation homeFormation;
    private Formation awayFormation;

public Match (IClub homeClub, IClub awayClub, ITeam homeTeam, ITeam awayTeam, int homeGoals, int awayGoals, Formation homeFormation, Formation awayFormation) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.homeFormation = homeFormation;
        this.awayFormation = awayFormation;
}


    @Override
    public IClub getHomeClub() {
        return this.homeClub;
    }

    @Override
    public IClub getAwayClub() {
        return this.awayClub;
    }

    @Override
   public ITeam getHomeTeam() {
   return this.homeTeam;
}

    @Override
    public ITeam getAwayTeam(){
        return this.awayTeam;
    }

    public int getHomeGoals() {
        return this.homeGoals;
    }

    public int getAwayGoals() {
        return this.awayGoals;
    }

    public Formation getHomeFormation() {
        return this.homeFormation;
    }

    public Formation getAwayFormation() {
        return this.awayFormation;
    }
    
    public String getResult() {
        
        return homeClub.getName() + " " + homeGoals + " - " + awayGoals + " " + awayClub.getName();
}
     @Override
    public String toString() {
        return "Match between " + homeClub.getName() + " and " + awayClub.getName();
    }
}
