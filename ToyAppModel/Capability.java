class Capability {
    private Role role;

    public Capability(Role role) {
        this.role = role;
    }

    public boolean hasPermission(Resource res, Operation op) {
        return role.hasPermission(res, op);
    }

    // Getters and setters as needed

}