package client.tags;

public enum TagsMsg {
    TAG_BRC_LIST_CLIENTS {
        public String toString() {
            return "<broadcastListClients@1122>";
        }
    },

    TAG_REQ_SET_NAME {
        public String toString() {
            return "<setName@1122>";
        }
    }
}
