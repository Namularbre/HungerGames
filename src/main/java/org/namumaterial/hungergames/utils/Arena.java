package org.namumaterial.hungergames.utils;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Arena {
    private final Location center;
    private double radius;
    private final double endRadius;

    public Arena(Location center, double radius) {
        this.center = center;
        this.radius = radius;
        this.endRadius = 200.0;
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

    public void reduceRadius(double value) {
        this.radius = Math.max(this.radius - value, this.endRadius);
    }

    public boolean isReductionFinished() {
        return this.radius > this.endRadius;
    }
}
