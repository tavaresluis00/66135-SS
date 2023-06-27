class Role {
    private String roleid;
    private Map<Resource, Set<Operation>> permissions;

    public Role(String roleid) {
        this.roleid = roleid;
        this.permissions = new HashMap<>();
    }

    public void grantPermission(Resource res, Operation op) {
        Set<Operation> ops = permissions.getOrDefault(res, new HashSet<>());
        ops.add(op);
        permissions.put(res, ops);
    }

    public void revokePermission(Resource res, Operation op) {
        Set<Operation> ops = permissions.getOrDefault(res, new HashSet<>());
        ops.remove(op);
        if (ops.isEmpty()) {
            permissions.remove(res);
        } else {
            permissions.put(res, ops);
        }
    }

    public boolean hasPermission(Resource res, Operation op) {
        Set<Operation> ops = permissions.getOrDefault(res, new HashSet<>());
        return ops.contains(op);
    }


}