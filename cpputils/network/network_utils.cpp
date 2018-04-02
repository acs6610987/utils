#include "network_utils.h"
#include <sstream>
#include "byte_stream.h"

using namespace std;


namespace network
{
    
    void send_int(int data, Channel &channel)
    {
        std::unique_ptr<ByteStream> buff(new ByteStream());
        buff->resize(sizeof(int));
        buff->getArrayView<int>()[0] = data;
        channel.asyncSend(std::move(buff));
    }

    void receive_int(int &data, Channel &channel)
    {
        ByteStream buff;
        channel.recv(buff);
        data = buff.getArrayView<int>()[0];
    }

    void send_uint64(uint64_t data, Channel &channel)
    {
        std::unique_ptr<ByteStream> buff(new ByteStream());
        buff->resize(sizeof(uint64_t));
        buff->getArrayView<uint64_t>()[0] = data;
        channel.asyncSend(std::move(buff));
    }

    void receive_uint64(uint64_t &data, Channel &channel)
    {
        ByteStream buff;
        channel.recv(buff);
        data = buff.getArrayView<uint64_t>()[0];
    }

    void send_string(const std::string &data, Channel &channel)
    {
        unique_ptr<ByteStream> buff(new ByteStream(reinterpret_cast<const uint8_t*>(data.data()), data.size()));
        channel.asyncSend(std::move(buff));
    }

    void receive_string(string &data, Channel &channel)
    {
        ByteStream buff;
        channel.recv(buff);
        data = string((char*)buff.data(), buff.size());
    }
    
    vector<string> split(string s, char delim)
    {
        vector<string> result;
        stringstream ss(s);
        string item;
        while(getline(ss, item, delim))
        {
            if(item.empty())
                continue;
            result.push_back(item);
        }
        return result;
    }

}
