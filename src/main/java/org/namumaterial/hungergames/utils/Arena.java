package org.namumaterial.hungergames.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Arena {
    private Location center;
    private double radius;
    private double endRadius;
    private boolean reductionFinished;

    public Arena(Location center, double radius) {
        this.center = center;
        this.radius = radius;
        this.endRadius = 200.0;
        this.reductionFinished = false;
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
        if (!this.reductionFinished) {
            Bukkit.getServer().broadcastMessage("Arena radius : " + this.radius);
            this.radius = this.radius - value;
            return;
        }

        if (this.radius <= this.endRadius) {
            this.reductionFinished = true;
        }
    }

    public boolean isReductionFinished() {
        return this.reductionFinished;
    }
}
