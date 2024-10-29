#include<iostream>
#include<string>
#include<vector>
using namespace std;

int board[10][10];
int copy_board[10][10];
bool visited[10];
int result[10];

bool calc(int row, int cnt){
    int index=0;

    for(int j=0;j<9;j++){
        if(copy_board[row][j]==0){
            int target=result[index];

            for(int i=0;i<9;i++){
                if(copy_board[i][j] == target){
                    return false;
                }
            }
            index++;
        }
    }

    for(int j=0;j<9;j++){
        if(copy_board[row][j]==0){
            copy_board[row][j]=result[index++];
        }
    }

    return true;
}

void perm(vector<int> v, int cnt, int total, int row){
    if(cnt==total){
        // for(int i=0;i<cnt;i++){
        //     cout << result[i] << " ";
        // }
        // cout << "\n";
        bool res = calc(row,total);
        if(res)
        return;
    }

    for(int i=0;i<total;i++){
        if(visited[i]){
            continue;
        }

        result[cnt]=v[i];
        visited[i]=true;
        perm(v,cnt+1,total, row);
        visited[i]=false;
    }
}

int main(){
    for(int i=0;i<9;i++){
        string s;
        cin >> s;

        for(int j=0;j<9;j++){
            board[i][j]=s[j]-'0';
        }
    }

    for(int i=0;i<9;i++){

        for(int k=0;k<9;k++){
            for(int m=0;m<9;m++){
                copy_board[k][m]=board[k][m];
            }
        }

        vector<int> v;
        bool has[10]={false,};

        for(int j=0;j<9;j++){
            if(board[i][j]!=0){
                has[board[i][j]]=true;
            }
        }

        for(int j=1;j<=9;j++){
            if(!has[j]){
                v.push_back(j);
            }
        }

        perm(v,0,v.size(),i);

        for(int j=0;j<v.size();j++){
            cout << v[j] << " ";
        }
        cout << "\n";
    }

    // cout << "\n\n";
    // vector<int> tmp;
    // tmp.push_back(2);
    // tmp.push_back(4);
    // tmp.push_back(6);
    // tmp.push_back(7);
    // tmp.push_back(8);
    // perm(tmp,0,5);
}