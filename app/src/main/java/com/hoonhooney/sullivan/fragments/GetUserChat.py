import socket
import time
from chatterbot import ChatBot
from chatterbot.trainers import ListTrainer

conversation = [
    "오늘 기분은 좋네요",
    "오늘 정말 멋져보여요.",
    "저도 그거 되게 좋아하는데! 마음이 통했네요.",
    "우울해 하지 마세요... 힘내요",
    "이해를 잘 하지 못했어요. 서비스를 개선하려고 노력 중이에요. 이해해주세요."
 ]

chatbot = ChatBot('또미')

host = socket.gethostbyname(socket.gethostname())
port = 7777

server_sock = socket.socket(socket.AF_INET)
server_sock.bind((host, port))
server_sock.listen(1)

trainer = ListTrainer(chatbot)

trainer.train(conversation)

while(True):
    print("기다리는 중")
    client_sock, addr = server_sock.accept()

    print('Connected by', addr)


    data = client_sock.recv(1024)
    data = data.decode("utf-8")
    data2 = chatbot.get_response(data)

    data2 = data2.text

    client_sock.send(data2.encode())
    client_sock.close()

