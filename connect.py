import socket
HOST = 'localhost'
PORT = 8091
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    sock.connect((HOST, PORT))
    while True:
    # for i in range(3000000):
        data = sock.recv(4)
        res = int.from_bytes(data, "big")
        # print(res)
except Exception as e:
    print(e)
    print("Cannot connect to the server")

