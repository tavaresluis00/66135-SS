import java.util.HashMap;
import java.util.Map;

class AccessController {
    private Map<String, Role> roles;

    public AccessController() {
        this.roles = new HashMap<>();
        initializeRoles();
    }

    private void initializeRoles(String roleid) {
        if(roleid == "normal"){
            // Creating "normal" author role
            Role normalAuthorRole = new Role("normal");
            normalAuthorRole.grantPermission(PageResource.getInstance(), AccessPageOperation.getInstance());
            normalAuthorRole.grantPermission(FollowRequestResource.getInstance(), SubmitFollowRequestOperation.getInstance());
            normalAuthorRole.grantPermission(FollowRequestResource.getInstance(), AuthorizeFollowRequestOperation.getInstance());
            normalAuthorRole.grantPermission(PostResource.getInstance(), AccessPostOperation.getInstance());
            normalAuthorRole.grantPermission(PostResource.getInstance(), CreatePostOperation.getInstance());
            normalAuthorRole.grantPermission(PostResource.getInstance(), DeleteOwnPostOperation.getInstance());
            normalAuthorRole.grantPermission(LikePostResource.getInstance(), LikeUnlikePostOperation.getInstance());
            roles.put(normalAuthorRole.getRoleid(), normalAuthorRole);
        } else if (roleid == "admin") {
            Role adminAuthorRole = new Role("admin");
            adminAuthorRole.grantPermissionAll(normalAuthorRole); // Inherits all permissions from "normal" author role
            adminAuthorRole.grantPermission(PageResource.getInstance(), CreatePageOperation.getInstance());
            adminAuthorRole.grantPermission(PageResource.getInstance(), DeletePageOperation.getInstance());
            roles.put(adminAuthorRole.getRoleid(), adminAuthorRole);
        }

    }

    public Role newRole(String roleid) {
        Role role = new Role(roleid);
        roles.put(roleid, role);
        return role;
    }

    public void setRole(Account user, Role role) {
        user.setRole(role);
    }

    public Role getRole(Account user) {
        return user.getRole();
    }

    public void grantPermission(Role role, Resource res, Operation op) {
        role.grantPermission(res, op);
    }

    public void revokePermission(Role role, Resource res, Operation op) {
        role.revokePermission(res, op);
    }

    public Capability makeKey(Role role) {
        return new Capability(role);
    }

    public void checkPermission(Capability cap, Resource res, Operation op) {
        if (cap.hasPermission(res, op)) {
            System.out.println("Permission granted");
        } else {
            System.out.println("Permission denied");
        }
    }
}