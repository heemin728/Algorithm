#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

string s,t;
int answer = 0;

void solution(string str){
    if(str.size() == s.size()){
        if(str == s){
            answer = 1;
            return;
        }
        return;
    }

    if(str[0] == 'B'){
        string tmp;
        for(int i = str.size() - 1;i>0;i--){
            tmp += str[i];
        }
        solution(tmp);
    }
    if(str[str.size() - 1] == 'A'){
        string tmp;
        for(int i = 0;i<str.size() - 1;i++){
            tmp += str[i];
        }
        solution(tmp);
    }
    return;
}

int main(){
    cin >> s >> t;
    
    solution(t);

    cout << answer << "\n";
    
    return 0;
}
