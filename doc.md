## 需求清单

1. 用户发布产品体验
2. 用户进行用户间的私聊
3. 用户自定义tag（系统会预留一部分tag）
4. 用户根据tag获得内容，包括得到选中tag内容和系统根据tag推荐两种方式
5. 用户购买文化特色商品
6. 用户发起定制需求
7. 管理员审批用户发帖
8. 管理员发布深度文化遗产资讯
9. 管理员提供产品购买渠道（可根据用户需求发起）

## 接口清单
### 1 message
class Message {   
	long messageId;   
	long userId;   
	List<Tag> tags;    
	String title;    
	String content;         
	boolean isDeleted;    
	MessageState state;//包括待审批、审批不通过、审批成功等    
}    

1. ResultMessage addMessage(Message message);
2. Boolean checkMessage(long messageId);
3. ResultMessage modifyMessage(Message message);
4. Message findMessageById(long mid);
5. List<Message> findMessageByTag(Tag tag);
6. etc,查询的接口到时候再说吧

class PrivateMessage {    
	long pmId;    
	long senderId;    
	long receiverId;    
	boolean isDeleted;    
	String title;    
	String content;    
}

1. ResultMessage sendPrivateMes(PrivateMessage pm);
2. List<PrivateMessage> findSentMes(long senderId);
3. List<PrivateMessage> findReceivedMes(long receiverId);
4. 其他查询接口

### 2 tag

class Tag {    
	long tagId;    
	boolean isDeleted;    
	String tagContent;//暂定不允许重名    
}

1. ResultMessage addTag(Tag tag);
2. ResultMessage modifyTag(Tag tag);
3. List<Tag> findTagById(long tagId);
4. etc

### 3 user

class User {    
	long userId;    
	String name;    
	String password;    
	String role;    
	boolean isDeleted;    
	double balance;     
	List<Tag> tags;    
}   
增、改、查、修改密码、重置密码等

### 4 shopping

class Commodity {    
	long cid;    
	long vendorId;    
	boolean isDeleted;    
	double price;    
	int remainedQuantity;    
	String description;    
	//etc,暂时想到那么多    
}

1. 增删改查
2. ResultMessage buyCommodity(long cid, int quantity);

class Inquiry {    
	long inquiryId;    
	long senderId;    
	String content;    
	boolean isDeleted;    
	InquiryState state;//包括开发中、已满足等     
}

1. ResultMessage addInquiry(Inquiry inquiry);
2. ResultMessage addCommodityByInquiry(Commodity commodity, long inquiryId);//限管理员
3. Commodity findCommodityByInquiry(long inquiryId);

class Purchase {        
	long cid;    
	long buyerId;    
	int quantity;    
	boolean isDeleted;    
}

增、查

### 5 recommendation

class Recommendation {    
	long rid;    
	String content;    
	boolean isDeleted;    
	List<Tag> tags;//我认为深度资讯的推荐直接根据tag做比较好       
}

## 注意事项

在填写对象的相关信息时，不需填写id，传送到后段后生成id