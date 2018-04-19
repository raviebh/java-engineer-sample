package com.att.demo.resource;

/**
 * This is the Interface definition for Account mService
 * 
 * 
 */
@Controller
public class UserResourceImpl implements UserResource {

    public static final Logger logger = LoggerFactory.getLogger(UserResourceImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    private static String baseUrl = "/users";


    @Override
    public Response findAllUsers() {
        return null;
    }

    @Override
    public Response getUser(Integer id) {
        logger.info("**************** resource **************");
        User user = userService.getUser(Long.valueOf(id));
        List<User> userList = null;
        if(user != null){
            userList = new ArrayList<User>();
            userList.add(user);
            Link link = Link.fromUri(baseUrl).rel("self").build();
            ResourceCollection<User> resource = new ResourceCollection<>(userList);
            return Response.ok(resource).links(link).build();
        } else {
            return Response.noContent().build();
        }

    }

    @Override
    public Response createUser(User user) {
        logger.info("**************** resource **************");
        /*Account account = accountService.findById(user.getAccountId());
        if(account != null){
            Account account1 = new Account();
            account1.setId(user.getId(););
            account1.setName(user.getName());
            accountService.saveAccount(account1);

        }*/
        userService.createUser(user);
        return Response.noContent().build();
    }
}
