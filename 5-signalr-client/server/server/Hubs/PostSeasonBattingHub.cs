using SignalR.Hubs;

namespace server.Hubs
{
    [HubName("chatHub")]
    public class ChatHub : Hub
    {
        public void Distribute(string name, string message)
        {
            Clients.receive(string.Format("{0} => {1}<br/>", name, message));
        }
        
    }
}