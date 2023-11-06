import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashMap;

class HuffmanNode {
    char data;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = null;
        right = null;
    }
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.frequency - y.frequency;
    }
}

class HuffmanEncoding {
    public static void printHuffmanCodes(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }
        if (root.data != '\0') {
            System.out.println(root.data + ":" + code);
        }
        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    public static String encodeHuffman(HuffmanNode root, String text) {
        StringBuilder encodedText = new StringBuilder();
        HashMap<Character, String> huffmanCodeMap = new HashMap<>();
        buildHuffmanCodes(root, "", huffmanCodeMap);

        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodeMap.get(c));
        }

        return encodedText.toString();
    }

    public static void buildHuffmanCodes(HuffmanNode root, String code, HashMap<Character, String> huffmanCodeMap) {
        if (root == null) {
            return;
        }
        if (root.data != '\0') {
            huffmanCodeMap.put(root.data, code);
        }
        buildHuffmanCodes(root.left, code + "0", huffmanCodeMap);
        buildHuffmanCodes(root.right, code + "1", huffmanCodeMap);
    }

    public static String decodeHuffman(HuffmanNode root, String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }

            if (current.data != '\0') {
                decodedText.append(current.data);
                current = root; // Reset to the root for the next character
            }
        }

        return decodedText.toString();
    }

    public static void main(String[] args) {
        String text = "this is an example for huffman encoding";
        System.out.println("Original Text:");
        System.out.println(text);

        HuffmanNode root = buildHuffmanTree(text);
        System.out.println("Huffman Codes:");
        printHuffmanCodes(root, "");

        String encodedMessage = encodeHuffman(root, text);
        System.out.println("Encoded Message:");
        System.out.println(encodedMessage);

        String decodedMessage = decodeHuffman(root, encodedMessage);
        System.out.println("Decoded Message:");
        System.out.println(decodedMessage);
    }

    public static HuffmanNode buildHuffmanTree(String text) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new MyComparator());

        for (char c : frequencyMap.keySet()) {
            priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
        }
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode merged = new HuffmanNode('\0', left.frequency + right.frequency);
            merged.left = left;
            merged.right = right;

            priorityQueue.add(merged);
        }
        return priorityQueue.poll();
    }
}