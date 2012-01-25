package cc.co.evenprime.bukkit.nocheat.checks.moving;

import java.util.Locale;
import cc.co.evenprime.bukkit.nocheat.NoCheat;
import cc.co.evenprime.bukkit.nocheat.NoCheatPlayer;
import cc.co.evenprime.bukkit.nocheat.actions.ParameterName;
import cc.co.evenprime.bukkit.nocheat.checks.Check;
import cc.co.evenprime.bukkit.nocheat.config.ConfigurationCacheStore;
import cc.co.evenprime.bukkit.nocheat.data.DataStore;
import cc.co.evenprime.bukkit.nocheat.data.PreciseLocation;

public abstract class MovingCheck extends Check {

    private static final String id = "moving";

    public MovingCheck(NoCheat plugin, String name, String permission) {
        super(plugin, id, name, permission);
    }

    /**
     * Return a new destination location or null
     * 
     * @param event
     * @return
     */
    public abstract PreciseLocation check(final NoCheatPlayer player, MovingData data, MovingConfig cc);

    public abstract boolean isEnabled(MovingConfig moving);

    @Override
    public String getParameter(ParameterName wildcard, NoCheatPlayer player) {

        if(wildcard == ParameterName.LOCATION) {
            PreciseLocation from = getData(player.getDataStore()).from;
            return String.format(Locale.US, "%.2f,%.2f,%.2f", from.x, from.y, from.z);
        } else if(wildcard == ParameterName.MOVEDISTANCE) {
            PreciseLocation from = getData(player.getDataStore()).from;
            PreciseLocation to = getData(player.getDataStore()).to;
            return String.format(Locale.US, "%.2f,%.2f,%.2f", to.x - from.x, to.y - from.y, to.z - from.z);
        } else if(wildcard == ParameterName.LOCATION_TO) {
            PreciseLocation to = getData(player.getDataStore()).to;
            return String.format(Locale.US, "%.2f,%.2f,%.2f", to.x, to.y, to.z);
        } else
            return super.getParameter(wildcard, player);

    }

    public static MovingData getData(DataStore base) {
        MovingData data = base.get(id);
        if(data == null) {
            data = new MovingData();
            base.set(id, data);
        }
        return data;
    }

    public static MovingConfig getConfig(ConfigurationCacheStore cache) {
        MovingConfig config = cache.get(id);
        if(config == null) {
            config = new MovingConfig(cache.getConfiguration());
            cache.set(id, config);
        }
        return config;
    }
}
