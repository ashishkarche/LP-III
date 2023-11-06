'''
Name :- Ashish Karche
Div-Roll No :- A-54
LP-III Assignment - 2

'''

class Node:
    def __init__(self, prob, symbol, left=None, right=None):
        self.prob = prob
        self.symbol = symbol
        self.left = left
        self.right = right
        self.code = ''

def Calculate_prob(data):
    symbols = {}
    for element in data:
        if element in symbols:
            symbols[element] += 1
        else:
            symbols[element] = 1
    return symbols

def Output_Encode(data, coding):
    encoding_output = []
    for c in data:
        encoding_output.append(coding[c])
    string = ''.join(encoding_output)
    return string

def Total_Gain(data, coding):
    before_compression = len(data) * 8
    after_compression = 0

    for symbol in data:
        after_compression += len(coding[symbol])

    print("Space usage before compression (in bits):", before_compression)
    print("Space usage after compression (in bits):", after_compression)

def Calculate_Codes(node, code='', mapping=None):
    if mapping is None:
        mapping = {}
    if node:
        if node.symbol:
            mapping[node.symbol] = code
        Calculate_Codes(node.left, code + '0', mapping)
        Calculate_Codes(node.right, code + '1', mapping)
    return mapping

def Huffman_Encoding(data):
    symbol_with_prob = Calculate_prob(data)
    symbols = symbol_with_prob.keys()
    probabilities = symbol_with_prob.values()
    print("Symbols:", symbols)
    print("probabilities:", probabilities)

    nodes = []

    for symbol in symbols:
        nodes.append(Node(symbol_with_prob.get(symbol), symbol))

    while len(nodes) > 1:
        nodes = sorted(nodes, key=lambda x: x.prob)

        right = nodes[0]
        left = nodes[1]

        left.code = '0'
        right.code = '1'

        newNode = Node(left.prob + right.prob, left.symbol + right.symbol, left, right)

        nodes.remove(left)
        nodes.remove(right)
        nodes.append(newNode)

    Huffman_Encoding = Calculate_Codes(nodes[0])
    print("symbols with codes", Huffman_Encoding)
    Total_Gain(data, Huffman_Encoding)
    encoded_output = Output_Encode(data, Huffman_Encoding)
    print("Encoding output:", encoded_output)
    return encoded_output, nodes[0]

data = "AAAAAAABCCCCCCDDEEEEE"
Huffman_Encoding(data)
