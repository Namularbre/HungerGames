package org.namumaterial.hungergames.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;

// The arena is the vanilla world border : visible by the players, and it damages them by itself.
public class Arena {
    // The border shrinks of ARENA_REDUCING_VALUE blocks every ARENA_REDUCING_PERIOD seconds
    private static final double ARENA_REDUCING_PERIOD = 10.0;

    // Players' screen turns red when they are this close to the border
    private static final int WARNING_DISTANCE = 50;
    // Damage per second, for each block the player is outside the border
    private static final double DAMAGE_AMOUNT = 0.5;
    private static final double DAMAGE_BUFFER = 0.0;
    private static final int OVERWORLD_INDEX = 0;

    private final Location center;
    private final double endRadius;
    private final WorldBorder border;
    private boolean shrinking;

    public Arena() {
        this.center = Bukkit.getServer().getWorlds().get(OVERWORLD_INDEX).getSpawnLocation();
        this.endRadius = HungerGamesConfiguration.ARENA_END_RADIUS;
        this.border = this.center.getWorld().getWorldBorder();

        this.border.setCenter(this.center);
        this.border.setWarningDistance(WARNING_DISTANCE);
        this.border.setDamageAmount(DAMAGE_AMOUNT);
        this.border.setDamageBuffer(DAMAGE_BUFFER);

        // The border size is saved with the world, so it must be reset when the plugin starts
        reset();
    }

    public void startShrinking() {
        this.shrinking = true;

        final double DISTANCE_TO_SHRINK = getRadius() - this.endRadius;

        if (DISTANCE_TO_SHRINK <= 0) {
            return;
        }

        final long SHRINKING_DURATION_IN_SECONDS = (long) (DISTANCE_TO_SHRINK / HungerGamesConfiguration.ARENA_REDUCING_VALUE * ARENA_REDUCING_PERIOD);

        this.border.setSize(radiusToSize(this.endRadius), SHRINKING_DURATION_IN_SECONDS);
    }

    public void stopShrinking() {
        this.shrinking = false;

        // Setting the current size cancels the running transition
        this.border.setSize(this.border.getSize());
    }

    public double getRadius() {
        return this.border.getSize() / 2;
    }

    public Location getCenter() {
        return center;
    }

    public double getEndRadius() {
        return endRadius;
    }

    public void setRadius(double radius) {
        this.border.setSize(radiusToSize(radius));

        if (this.shrinking) {
            startShrinking();
        }
    }

    public void reset() {
        this.shrinking = false;
        this.border.setSize(radiusToSize(HungerGamesConfiguration.ARENA_START_RADIUS));
    }

    // The border is a square : its size is its width, so twice the radius
    private static double radiusToSize(double radius) {
        return radius * 2;
    }
}
