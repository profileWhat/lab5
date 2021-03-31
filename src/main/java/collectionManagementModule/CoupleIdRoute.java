package collectionManagementModule;

/**
 * Couple Id-Route class
 */
public class CoupleIdRoute {
    private final Long id;
    private final Route route;

    public CoupleIdRoute(Long id, Route route) {
        this.id = id;
        this.route = route;
    }

    public Long getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }
}
