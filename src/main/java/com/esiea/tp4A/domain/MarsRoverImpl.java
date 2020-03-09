package com.esiea.tp4A.domain;

public class MarsRoverImpl implements MarsRover {
	private Position position;
	private PlanetMapImpl map;

	public MarsRover initialize(Position position) {
		this.position = position;
		this.map = (PlanetMapImpl) new PlanetMapImpl().initialize();
		return this;
	}

	@Override
	public MarsRover updateMap(PlanetMap map) {
		this.map = (PlanetMapImpl) map;
		return this;
	}

	@Override
	public Position move(String command) {
		Position pos_next = this.position;

		switch (command) {
		case "f":
			switch (this.position.getDirection()) {
			case NORTH:
				pos_next = Position.of(pos_next.getX(), pos_next.getY() + 1, pos_next.getDirection());
				break;
			case SOUTH:
				pos_next = Position.of(pos_next.getX(), pos_next.getY() - 1, pos_next.getDirection());
				break;
			case EAST:
				pos_next = Position.of(pos_next.getX() + 1, pos_next.getY(), pos_next.getDirection());
				break;
			case WEST:
				pos_next = Position.of(pos_next.getX() - 1, pos_next.getY(), pos_next.getDirection());
				break;
			}
			break;
		case "b":
			switch (this.position.getDirection()) {
			case NORTH:
				pos_next = Position.of(pos_next.getX(), pos_next.getY() - 1, pos_next.getDirection());
				break;
			case SOUTH:
				pos_next = Position.of(pos_next.getX(), pos_next.getY() + 1, pos_next.getDirection());
				break;
			case EAST:
				pos_next = Position.of(pos_next.getX() - 1, pos_next.getY(), pos_next.getDirection());
				break;
			case WEST:
				pos_next = Position.of(pos_next.getX() + 1, pos_next.getY(), pos_next.getDirection());
				break;
			}
			break;
		case "l":
			switch (this.position.getDirection()) {
			case NORTH:
				pos_next = Position.of(pos_next.getX(), pos_next.getY(), Direction.WEST);
				break;
			case SOUTH:
				pos_next = Position.of(pos_next.getX(), pos_next.getY(), Direction.EAST);
				break;
			case WEST:
				pos_next = Position.of(pos_next.getX(), pos_next.getY(), Direction.SOUTH);
				break;
			case EAST:
				pos_next = Position.of(pos_next.getX(), pos_next.getY(), Direction.NORTH);
				break;
			}
			break;
		case "r":
			switch (this.position.getDirection()) {
			case NORTH:
				pos_next = Position.of(pos_next.getX(), pos_next.getY(), Direction.EAST);
				break;
			case SOUTH:
				pos_next = Position.of(pos_next.getX(), pos_next.getY(), Direction.WEST);
				break;
			case WEST:
				pos_next = Position.of(pos_next.getX(), pos_next.getY(), Direction.NORTH);
				break;
			case EAST:
				pos_next = Position.of(pos_next.getX(), pos_next.getY(), Direction.SOUTH);
				break;
			}
			break;
		}

        // check if map not null and if next position is not the position of an obstacle, if it's not : move to next
        if(this.map != null && !this.map.isPositionOnMap(pos_next))
            this.position = pos_next;

        return this.position;
	}

    public MarsRoverImpl configureLaserRange(int portee) {
        boolean detruit = false;
        for(int i=0; i<portee; i++){
            if (!detruit) {
                Position pos_next = this.position;
                switch (this.position.getDirection()) {
                    case NORTH:
                        pos_next = new Position.FixedPosition(pos_next.getX(), pos_next.getY() + 1, pos_next.getDirection());
                        if (this.map.obstaclePositions().equals(pos_next)) {
                            detruit = map.removeObstaclePosition(pos_next);
                        }
                        break;
                    case SOUTH:
                        pos_next = new Position.FixedPosition(pos_next.getX(), pos_next.getY() - 1, pos_next.getDirection());
                        if (this.map.obstaclePositions().equals(pos_next)) {
                            detruit = map.removeObstaclePosition(pos_next);
                        }
                        break;
                    case EAST:
                        pos_next = new Position.FixedPosition(pos_next.getX() + 1, pos_next.getY(), pos_next.getDirection());
                        if (this.map.obstaclePositions().equals(pos_next)) {
                            detruit = map.removeObstaclePosition(pos_next);
                        }
                        break;
                    case WEST:
                        pos_next = new Position.FixedPosition(pos_next.getX() - 1, pos_next.getY(), pos_next.getDirection());
                        if (this.map.obstaclePositions().equals(pos_next)) {
                            detruit = map.removeObstaclePosition(pos_next);
                        }
                        break;
                }
            }
        }
        return this;
    }

    public Position move(Character[] commands){
        for (Character command : commands) {
            this.move(Character.toString(command));
        }
        return this.position;
    }
}
