package shared;

import java.util.concurrent.CopyOnWriteArrayList;

public class Group implements MessageReceiver {
   private CopyOnWriteArrayList<User> members;
   private String groupName;

   public Group(String groupName,CopyOnWriteArrayList<User> members){
       this.members=members;
       this.groupName=groupName;
   }

    public CopyOnWriteArrayList<User> getMembers() {
        return members;
    }

    public void addMember(User member) {
        members.add(member);
    }
}
