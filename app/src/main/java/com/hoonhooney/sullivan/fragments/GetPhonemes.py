import socket
import time
from g2pk import G2p


g2p=G2p()

host = socket.gethostbyname(socket.gethostname())
port = 9999

server_sock = socket.socket(socket.AF_INET)
server_sock.bind((host, port))
server_sock.listen(1)

while(True):
    print("기다리는 중")
    client_sock, addr = server_sock.accept()

    print('Connected by', addr)


    data = client_sock.recv(1024)
    data = data.decode("utf-8")
    # data2 = int(input("보낼 값 : "))
    data2 = g2p(data)
    #print(data2.encode())
    #client_sock.send(data)
    #client_sock.send(data2.to_bytes(4, byteorder='little'))

    # 값하나 보냄(사용자가 입력한 숫자)
    # client_sock.send(data2.to_bytes(4, byteorder='little'))
    print(data2)
    client_sock.send(data2.encode())
    client_sock.close()



# 연결끊겠다는 표시 보냄


'''
print("연결 종료")
client_sock.close()
server_sock.close()



def main():
    get_data()

if __name__ =="__main__":
    main()
'''
