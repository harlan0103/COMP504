READEME:
1 slip day used

Design Decision:
In order to access all balls, I passed adapter into the IBallMoveCmd. The idea is that using adapter's method notifyObservers can access all balls. Therefore, for each ball in the ball world, call the method BallCollide() to detect if the current ball, i.e source ball collides with other balls, the dest ball. If the return value of BallCollide() is true, then call the user selected interaction strategy.
