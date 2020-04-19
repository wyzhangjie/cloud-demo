package algorithm.math;

import leetcode.stack.Stack;

public class Grap {

    public static void forDepth(Folder folder) {
        Stack<Folder> a = new Stack<>();
        Folder top = new Folder(folder.folderName, folder.folders);
        a.push(top);
        while (!a.empty()) {
            Folder f = a.pop();
            if(f.folders==null){
                System.out.println(f.folderName);
            }else {
                Folder[] subFolder = f.folders;
                Stack<Folder> temp = new Stack<>();
                for(int i=0;i<subFolder.length;i++){
                    if(subFolder[i]!=null){
                        temp.push(subFolder[i]);
                        f.folders[i]=null;
                    }

                }
                while(!temp.empty()){
                    a.push(temp.pop());
                }
            }


        }

    }

    public void forWedth(Folder f) {

    }

    public static class Folder {
        String folderName;
        Folder[] folders;

        public Folder(String folderName, Folder[] subFolder) {
            this.folderName = folderName;
            this.folders = subFolder;
        }
    }

    public static void main(String[] args) {
        Folder folder1 = new Folder("第一层（第一个文件夹）", null);
        Folder folder2 = new Folder("第一层（第二个文件夹）", null);
        Folder folder3 = new Folder("第一层（第一个文件夹）", null);
        Folder[] folders1 = new Folder[]{folder1, folder3};
        Folder[] folders2 = new Folder[]{folder2};
        Folder folder1_2 = new Folder("第二层（第一个文件夹）", folders1);
        Folder folder2_2 = new Folder("第二层（第二个文件夹）", folders2);
        Folder[] folders3_1 = new Folder[]{folder1_2, folder2_2};
        Folder folder3_1 = new Folder("第三层（顶层）", folders3_1);
        forDepth(folder3_1);

    }
}
