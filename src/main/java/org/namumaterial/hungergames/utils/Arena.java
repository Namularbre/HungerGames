package org.namumaterial.hungergames.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Arena {
    private final Location center;
    private double radius;
    private final double endRadius;

    public Arena() {
        this.center = Bukkit.getServer().getWorld("world").getSpawnLocation();
        this.radius = HungerGamesConfiguration.ARENA_START_RADIUS;
        this.endRadius = HungerGamesConfiguration.ARENA_END_RADIUS;
    }

    public boolean isInsideRegion(Location location) {
        double dx = location.getX() - this.center.getX();
        double dz = location.getZ() - this.center.getZ();
        double distanceSquared = dx * dx + dz * dz;
        return distanceSquared <= this.radius * this.radius;
    }

    public boolean isInsideRegion(LivingEntity entity) {
        return this.isInsideRegion(entity.getLocation());
    }

    public boolean isNearBorder(Location playerLocation) {
        final double IS_NEAR_BORDER_RADIUS = 50.0;

        double dx = playerLocation.getX() - this.center.getX();
        double dz = playerLocation.getZ() - this.center.getZ();
        double distanceSquared = dx * dx + dz * dz;
        double distanceToBorder = this.radius - IS_NEAR_BORDER_RADIUS;

        return distanceSquared >= (distanceToBorder * distanceToBorder);
    }

    public boolean isNearBorder(LivingEntity entity) {
        return this.isNearBorder(entity.getLocation());
    }

    public void reduceRadius() {
        this.radius = Math.max(this.radius - HungerGamesConfiguration.ARENA_REDUCING_VALUE, this.endRadius);
    }

    public boolean isReductionFinished() {
        return this.radius <= this.endRadius;
    }

    public double getRadius() {
        return radius;
    }

    public Location getCenter() {
        return center;
    }

    public double getEndRadius() {
        return endRadius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
